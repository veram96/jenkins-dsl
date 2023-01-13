job('ejemplo2-job-dsl'){
  description('Job dsl de ejemplo')
  scm{
    git('https://github.com/macloujulian/jenkins.job.parametrizado.git','main'){ node ->
      node / gitConfigName("Miguel Vera")
      node / gitConfigEmail("angelvemo@gmail.com")
    }
  }
  parameters{
    stringParam('nombre', defaultValue='Miguel', description='string param for name')
    choiceParam('planeta', ['tierra', 'marte', 'jupiter', 'saturno'])
    booleanParam('agente', false)
  }
  triggers{
    cron('H/7 * * * *')
  }
  steps{
    shell('bash jobscript.sh')
  }
  publishers{
    mailer('puma_vera@outlook.com', true, true)
    slackNotifier {
      notifyAborted(true)
      notifyEveryFailure(true)
      notifyNotBuilt(false)
      notifyUnstable(false)
      notifyBackToNormal(true)
      notifySuccess(false)
      notifyRepeatedFailure(false)
      startNotification(false)
      includeTestSummary(false)
      includeCustomMessage(false)
      customMessage(null)
      sendAs(null)
      commitInfoChoice('NONE')
      teamDomain(null)
      authToken(null)
    }
  }
}