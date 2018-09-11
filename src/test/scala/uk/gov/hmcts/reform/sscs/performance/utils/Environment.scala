package uk.gov.hmcts.reform.sscs.performance.utils

object Environment {
  val httpConfig = scala.util.Properties.envOrElse("httpConfig", "http")
  
  //val cmcBashURL = scala.util.Properties.envOrElse("baseURL", "https://www-dev.moneyclaim.reform.hmcts.net")
  val cmcBashURL = scala.util.Properties.envOrElse("baseURL", "https://www.moneyclaims.demo.platform.hmcts.net")
  val sscsTYAURL = scala.util.Properties.envOrElse("baseURL", "https://track-appeal.demo.platform.hmcts.net")
 // val sscsSYAURL = scala.util.Properties.envOrElse("baseURL", "https://benefit-appeal.nonprod.platform.hmcts.net")
 //val sscsSYAURL = scala.util.Properties.envOrElse("baseURL", "https://benefit-appeal.demo.platform.hmcts.net")
  val sscsSYAURL = scala.util.Properties.envOrElse("baseURL", "https://sscs-tribunals-frontend-sprod.service.core-compute-sprod.internal")

  val PaymentURL = scala.util.Properties.envOrElse("paymentURL", "https://www.payments.service.gov.uk")
  
  val users = scala.util.Properties.envOrElse("numberOfUser", "10")
  val maxResponseTime = scala.util.Properties.envOrElse("maxResponseTime", "500")
  val idamCookieName="SESSION_ID"
  
  val commonHeader = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
		"Accept-Encoding" -> "gzip, deflate, br",
		"Accept-Language" -> "en-GB,en-US;q=0.9,en;q=0.8",
		"Connection" -> "keep-alive",
		"Upgrade-Insecure-Requests" -> "1",
		"User-Agent" -> "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36")

    
    val thinkTime = 1
  val tyaThinkTime=10
    val waitForNextIteration = 80
}