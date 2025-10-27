def call(String prefix, String botToken, String chatId) {
  def jobName = env.JOB_NAME ?: "Unknown job"
  def buildStatus = currentBuild.currentResult ?: "UNKNOWN"
  def buildUrl = env.BUILD_URL ?: "none"


  def tmpl = libraryResource 'telegramMessageTmpl.md'
  def msg = tmpl
        .replace('${jobName}', jobName)
        .replace('${buildStatus}', buildStatus)
        .replace('${buildUrl}', buildUrl)
        .replace('${prefix}', prefix)
  

  sh """
    curl -s -X POST https://api.telegram.org/bot${botToken}/sendMessage \
      -d chat_id=${chatId} \
      -d parse_mode=Markdown \
      --data-urlencode text=${msg}
  """
}
