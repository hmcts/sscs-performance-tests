package uk.gov.hmcts.reform.sscs.performance.utils

object Environment {

  val env = "perftest"
  val httpConfig = scala.util.Properties.envOrElse("httpConfig", "http")
  val SSCSCORURL = scala.util.Properties.envOrElse("basetyaURL", "https://sscs-cor." + env + ".platform.hmcts.net")
 /* val SSCSCORURL = scala.util.Properties.envOrElse("basetyaURL", "http://10.10.46.123")*/
  //val sscsSYAURL = scala.util.Properties.envOrElse("baseURL", "https://benefit-appeal.nonprod.platform.hmcts.net")
  val sscsSYAURL = scala.util.Properties.envOrElse("basesyaURL", "https://benefit-appeal." + env + ".platform.hmcts.net")
  val IDAMURL = scala.util.Properties.envOrElse("idamURL", "https://idam-web-public." + env + ".platform.hmcts.net")
  //val idamBaseUrl-old = "http://idam-api-perftest.service.core-compute-perftest.internal"
  val idamBaseUrl = "https://idam-api." + env + ".platform.hmcts.net"
  val PaymentURL = scala.util.Properties.envOrElse("paymentURL", "https://www.payments.service.gov.uk")

  
  val users = scala.util.Properties.envOrElse("numberOfUser", "10")
  val maxResponseTime = scala.util.Properties.envOrElse("maxResponseTime", "500")
  val idamCookieName="SESSION_ID"
  val mrnDate="06"
  val mrnMonth="11"
  val mrnYear="2020"

  val firstName="TestPF"
  val lastName="TesterPF"

  
  val commonHeader = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Accept-Language" -> "en-US,en;q=0.9",
    "Sec-Fetch-Mode" -> "navigate",
    "Sec-Fetch-Site" -> "same-site",
    "Sec-Fetch-User" -> "?1",
    "Upgrade-Insecure-Requests" -> "1")

    
    val thinkTime = 80
  val thinkTimeNoUpload = 750
  val tyaThinkTime=300
    val waitForNextIteration = 20
}
