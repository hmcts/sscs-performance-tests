
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulationtrack extends Simulation {

	val httpProtocol = http
		.baseURL("https://track-appeal.demo.platform.hmcts.net")
		.inferHtmlResources()
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36")

	val headers_0 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
		"Accept-Encoding" -> "gzip, deflate, br",
		"Accept-Language" -> "en-US,en;q=0.9",
		"Cache-Control" -> "max-age=0",
		"Connection" -> "keep-alive",
		"Origin" -> "https://track-appeal.demo.platform.hmcts.net",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_1 = Map(
		"Accept" -> "text/css,*/*;q=0.1",
		"Accept-Encoding" -> "gzip, deflate, br",
		"Accept-Language" -> "en-US,en;q=0.9",
		"Connection" -> "keep-alive")

	val headers_4 = Map(
		"Accept" -> "image/webp,image/apng,image/*,*/*;q=0.8",
		"Accept-Encoding" -> "gzip, deflate, br",
		"Accept-Language" -> "en-US,en;q=0.9",
		"Connection" -> "keep-alive")

	val headers_6 = Map(
		"Accept" -> "*/*",
		"Accept-Encoding" -> "gzip, deflate, br",
		"Accept-Language" -> "en-US,en;q=0.9",
		"Connection" -> "keep-alive")

	val headers_24 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
		"Accept-Encoding" -> "gzip, deflate, br",
		"Accept-Language" -> "en-US,en;q=0.9",
		"Connection" -> "keep-alive",
		"Upgrade-Insecure-Requests" -> "1")

    val uri2 = "https://www.google-analytics.com"

	val scn = scenario("RecordedSimulationtrack")
		.exec(http("request_0")
			.post("/validate-surname/fcY56vNmBR/trackyourappeal")
			.headers(headers_0)
			.formParam("surname", "Perfvenme"))
		.pause(1)
		.exec(http("request_1")
			.get("/public/stylesheets/govuk-template.css?0.22.3")
			.headers(headers_1)
			.resources(http("request_2")
			.get("/public/stylesheets/fonts.css?0.22.3")
			.headers(headers_1),
            http("request_3")
			.get("/public/stylesheets/application.css")
			.headers(headers_1),
            http("request_4")
			.get("/public/images/gov.uk_logotype_crown_invert_trans.png?0.22.3")
			.headers(headers_4),
            http("request_5")
			.get("/public/images/tick.png")
			.headers(headers_4),
            http("request_6")
			.get("/public/javascripts/govuk-template.js?0.22.3")
			.headers(headers_6),
            http("request_7")
			.get("/public/javascripts/jquery-1.12.2.js")
			.headers(headers_6),
            http("request_8")
			.get("/public/javascripts/google-analytics-universal-tracker.js")
			.headers(headers_6),
            http("request_9")
			.get("/public/javascripts/analytics.js")
			.headers(headers_6),
            http("request_10")
			.get("/public/javascripts/govuk-tracker.js")
			.headers(headers_6),
            http("request_11")
			.get("/public/javascripts/tya-analytics-tracker.js")
			.headers(headers_6),
            http("request_12")
			.get("/public/javascripts/details.polyfill.js")
			.headers(headers_6),
            http("request_13")
			.get("/public/javascripts/govuk/shim-links-with-button-role.js")
			.headers(headers_6),
            http("request_14")
			.get("/public/javascripts/govuk/show-hide-content.js")
			.headers(headers_6),
            http("request_15")
			.get("/public/javascripts/sscs.js")
			.headers(headers_6),
            http("request_16")
			.get("/public/javascripts/govuk-template.js")
			.headers(headers_6),
            http("request_17")
			.get("/public/stylesheets/govuk-template-print.css?0.22.3")
			.headers(headers_1)))
		.pause(1)
		.exec(http("request_18")
			.get("/public/images/tick.png")
			.headers(headers_4)
			.resources(http("request_19")
			.get("/public/stylesheets/images/govuk-crest.png?0.22.3")
			.headers(headers_4),
            http("request_20")
			.get("/public/stylesheets/images/open-government-licence.png?0.22.3")
			.headers(headers_4),
            http("request_21")
			.get("/public/stylesheets/images/gov.uk_logotype_crown.png?0.22.3")
			.headers(headers_4),
            http("request_22")
			.get(uri2 + "/analytics.js"),
            http("request_23")
			.get(uri2 + "/collect?v=1&_v=j68&aip=1&a=1600682832&t=pageview&_s=1&dl=https%3A%2F%2Ftrack-appeal.demo.platform.hmcts.net%2Ftrackyourappeal%2FfcY56vNmBR&ul=en-us&de=UTF-8&dt=Track%20your%20appeal%20-%20Appeal%20a%20benefit%20decision%20-%20GOV.UK&sd=24-bit&sr=1280x720&vp=1243x398&je=0&_u=QAGAAAAB~&cid=254763792.1527585292&tid=UA-91309785-1&_gid=2027557329.1531830559&z=216937041")))
		.pause(14)
		.exec(http("request_24")
			.get("/abouthearing/fcY56vNmBR")
			.headers(headers_24))
		.pause(1)
		.exec(http("request_25")
			.get("/public/stylesheets/govuk-template.css?0.22.3")
			.headers(headers_1)
			.resources(http("request_26")
			.get("/public/stylesheets/fonts.css?0.22.3")
			.headers(headers_1),
            http("request_27")
			.get("/public/stylesheets/application.css")
			.headers(headers_1),
            http("request_28")
			.get("/public/images/gov.uk_logotype_crown_invert_trans.png?0.22.3")
			.headers(headers_4),
            http("request_29")
			.get("/public/images/hearing-room-pip.png")
			.headers(headers_4),
            http("request_30")
			.get("/public/javascripts/govuk-template.js?0.22.3")
			.headers(headers_6),
            http("request_31")
			.get("/public/javascripts/jquery-1.12.2.js")
			.headers(headers_6),
            http("request_32")
			.get("/public/javascripts/google-analytics-universal-tracker.js")
			.headers(headers_6),
            http("request_33")
			.get("/public/javascripts/analytics.js")
			.headers(headers_6),
            http("request_34")
			.get("/public/javascripts/govuk-tracker.js")
			.headers(headers_6),
            http("request_35")
			.get("/public/javascripts/tya-analytics-tracker.js")
			.headers(headers_6),
            http("request_36")
			.get("/public/javascripts/details.polyfill.js")
			.headers(headers_6),
            http("request_37")
			.get("/public/javascripts/govuk/shim-links-with-button-role.js")
			.headers(headers_6),
            http("request_38")
			.get("/public/javascripts/govuk/show-hide-content.js")
			.headers(headers_6),
            http("request_39")
			.get("/public/javascripts/sscs.js")
			.headers(headers_6),
            http("request_40")
			.get("/public/javascripts/govuk-template.js")
			.headers(headers_6),
            http("request_41")
			.get("/public/stylesheets/govuk-template-print.css?0.22.3")
			.headers(headers_1),
            http("request_42")
			.get("/public/stylesheets/images/govuk-crest.png?0.22.3")
			.headers(headers_4),
            http("request_43")
			.get("/public/stylesheets/images/open-government-licence.png?0.22.3")
			.headers(headers_4),
            http("request_44")
			.get("/public/stylesheets/images/gov.uk_logotype_crown.png?0.22.3")
			.headers(headers_4),
            http("request_45")
			.get(uri2 + "/analytics.js"),
            http("request_46")
			.get(uri2 + "/collect?v=1&_v=j68&aip=1&a=829399793&t=pageview&_s=1&dl=https%3A%2F%2Ftrack-appeal.demo.platform.hmcts.net%2Fabouthearing%2FfcY56vNmBR&ul=en-us&de=UTF-8&dt=What%20to%20expect%20at%20your%20hearing%20-%20Appeal%20a%20benefit%20decision%20-%20GOV.UK&sd=24-bit&sr=1280x720&vp=1243x398&je=0&_u=QAGAAAAB~&cid=254763792.1527585292&tid=UA-91309785-1&_gid=2027557329.1531830559&z=947952010")))
		.pause(16)
		.exec(http("request_47")
			.get("/evidence/fcY56vNmBR")
			.headers(headers_24))
		.pause(1)
		.exec(http("request_48")
			.get("/public/stylesheets/govuk-template.css?0.22.3")
			.headers(headers_1)
			.resources(http("request_49")
			.get("/public/stylesheets/fonts.css?0.22.3")
			.headers(headers_1),
            http("request_50")
			.get("/public/stylesheets/application.css")
			.headers(headers_1),
            http("request_51")
			.get("/public/images/gov.uk_logotype_crown_invert_trans.png?0.22.3")
			.headers(headers_4),
            http("request_52")
			.get("/public/javascripts/govuk-template.js?0.22.3")
			.headers(headers_6),
            http("request_53")
			.get("/public/javascripts/jquery-1.12.2.js")
			.headers(headers_6),
            http("request_54")
			.get("/public/javascripts/google-analytics-universal-tracker.js")
			.headers(headers_6),
            http("request_55")
			.get("/public/javascripts/analytics.js")
			.headers(headers_6),
            http("request_56")
			.get("/public/javascripts/govuk-tracker.js")
			.headers(headers_6),
            http("request_57")
			.get("/public/javascripts/tya-analytics-tracker.js")
			.headers(headers_6),
            http("request_58")
			.get("/public/javascripts/details.polyfill.js")
			.headers(headers_6),
            http("request_59")
			.get("/public/javascripts/govuk/shim-links-with-button-role.js")
			.headers(headers_6),
            http("request_60")
			.get("/public/javascripts/govuk/show-hide-content.js")
			.headers(headers_6),
            http("request_61")
			.get("/public/javascripts/sscs.js")
			.headers(headers_6),
            http("request_62")
			.get("/public/javascripts/govuk-template.js")
			.headers(headers_6),
            http("request_63")
			.get("/public/stylesheets/govuk-template-print.css?0.22.3")
			.headers(headers_1),
            http("request_64")
			.get("/public/stylesheets/images/govuk-crest.png?0.22.3")
			.headers(headers_4),
            http("request_65")
			.get("/public/stylesheets/images/open-government-licence.png?0.22.3")
			.headers(headers_4),
            http("request_66")
			.get("/public/stylesheets/images/gov.uk_logotype_crown.png?0.22.3")
			.headers(headers_4),
            http("request_67")
			.get(uri2 + "/analytics.js"),
            http("request_68")
			.get(uri2 + "/collect?v=1&_v=j68&aip=1&a=1358652069&t=pageview&_s=1&dl=https%3A%2F%2Ftrack-appeal.demo.platform.hmcts.net%2Fevidence%2FfcY56vNmBR&ul=en-us&de=UTF-8&dt=Benefit%20appeal%20evidence%20-%20Appeal%20a%20benefit%20decision%20-%20GOV.UK&sd=24-bit&sr=1280x720&vp=1243x398&je=0&_u=QAGAAAAB~&cid=254763792.1527585292&tid=UA-91309785-1&_gid=2027557329.1531830559&z=979850994")))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}