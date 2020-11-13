package uk.gov.hmcts.reform.sscs.performance.utils

object SSCSMYAHeaders {
 val baseURL = Environment.SSCSCORURL
 val IdamUrl = Environment.IDAMURL

 //below are the headers
 val headers_0 = Map(
  "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
  "Accept-Encoding" -> "gzip, deflate",
  "Accept-Language" -> "en-US,en;q=0.9",
  "Upgrade-Insecure-Requests" -> "1",
  "User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36")

 val headers_login = Map(
  "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
  "accept-encoding" -> "gzip, deflate, br",
  "accept-language" -> "en-US,en;q=0.9",
  "cache-control" -> "max-age=0",
  "origin" -> "https://idam-web-public.perftest.platform.hmcts.net",
  "sec-fetch-dest" -> "document",
  "sec-fetch-mode" -> "navigate",
  "sec-fetch-site" -> "same-origin",
  "sec-fetch-user" -> "?1",
  "upgrade-insecure-requests" -> "1",
  "user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36")

 val headers_9 = Map(
  "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
  "cache-control" -> "max-age=0",
  "content-type" -> "multipart/form-data; boundary=----WebKitFormBoundaryuOBA1cG2BApZkrL6",
  "origin" -> baseURL,
  "sec-fetch-dest" -> "document",
  "sec-fetch-mode" -> "navigate",
  "sec-fetch-site" -> "same-origin",
  "sec-fetch-user" -> "?1",
  "upgrade-insecure-requests" -> "1")

 val headers_20 = Map(
  "accept" -> "application/json, text/plain, */*",
  "accept-encoding" -> "gzip, deflate, br",
  "accept-language" -> "en-US,en;q=0.9",
  "sec-fetch-dest" -> "empty",
  "sec-fetch-mode" -> "cors",
  "sec-fetch-site" -> "same-origin",
  "user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36")

 val headers_postcode = Map(
  "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
  "accept-encoding" -> "gzip, deflate, br",
  "accept-language" -> "en-US,en;q=0.9",
  "cache-control" -> "max-age=0",
  "origin" -> baseURL,
  "sec-fetch-dest" -> "document",
  "sec-fetch-mode" -> "navigate",
  "sec-fetch-site" -> "same-origin",
  "sec-fetch-user" -> "?1",
  "upgrade-insecure-requests" -> "1",
  "user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36")

 val headers_tasklist = Map(
  "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
  "accept-encoding" -> "gzip, deflate, br",
  "accept-language" -> "en-US,en;q=0.9",
  "sec-fetch-dest" -> "document",
  "sec-fetch-mode" -> "navigate",
  "sec-fetch-site" -> "same-origin",
  "sec-fetch-user" -> "?1",
  "upgrade-insecure-requests" -> "1",
  "user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36")

 val headers_hearing = Map(
  "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
  "accept-encoding" -> "gzip, deflate, br",
  "accept-language" -> "en-US,en;q=0.9",
  "sec-fetch-dest" -> "document",
  "sec-fetch-mode" -> "navigate",
  "sec-fetch-site" -> "same-origin",
  "sec-fetch-user" -> "?1",
  "upgrade-insecure-requests" -> "1",
  "user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36")


 val headers_upload = Map(
  "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
  "accept-encoding" -> "gzip, deflate, br",
  "accept-language" -> "en-US,en;q=0.9",
  "cache-control" -> "max-age=0",
  "origin" -> baseURL,
  "sec-fetch-dest" -> "document",
  "sec-fetch-mode" -> "navigate",
  "sec-fetch-site" -> "same-origin",
  "sec-fetch-user" -> "?1",
  "upgrade-insecure-requests" -> "1",
  "user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36")

 val headers_73 = Map(
  "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
  "accept-encoding" -> "gzip, deflate, br",
  "accept-language" -> "en-US,en;q=0.9",
  "cache-control" -> "max-age=0",
  "content-type" -> "multipart/form-data; boundary=----WebKitFormBoundaryq7pXscVFWTLISBg4",
  "origin" -> baseURL,
  "sec-fetch-dest" -> "document",
  "sec-fetch-mode" -> "navigate",
  "sec-fetch-site" -> "same-origin",
  "sec-fetch-user" -> "?1",
  "upgrade-insecure-requests" -> "1",
  "user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36")


 val headers_upload3mb = Map(
  "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
  "accept-encoding" -> "gzip, deflate, br",
  "accept-language" -> "en-US,en;q=0.9",
  "cache-control" -> "max-age=0",
  "content-type" -> "multipart/form-data; boundary=----WebKitFormBoundary1zIKvDeQ1iOj4qPy",
  "origin" -> baseURL,
  "sec-fetch-dest" -> "document",
  "sec-fetch-mode" -> "navigate",
  "sec-fetch-site" -> "same-origin",
  "sec-fetch-user" -> "?1",
  "upgrade-insecure-requests" -> "1",
  "user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36")

 val headers_83 = Map(
  "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
  "accept-encoding" -> "gzip, deflate, br",
  "accept-language" -> "en-US,en;q=0.9",
  "cache-control" -> "max-age=0",
  "content-type" -> "multipart/form-data; boundary=----WebKitFormBoundaryjacOdkygBa1u93KL",
  "origin" -> baseURL,
  "sec-fetch-dest" -> "document",
  "sec-fetch-mode" -> "navigate",
  "sec-fetch-site" -> "same-origin",
  "sec-fetch-user" -> "?1",
  "upgrade-insecure-requests" -> "1",
  "user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36")

 val headers_homepage = Map(
  "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
  "Accept-Encoding" -> "gzip, deflate",
  "Accept-Language" -> "en-US,en;q=0.9",
  "Upgrade-Insecure-Requests" -> "1",
  "User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36")

 val headers_fire2MB = Map(
  "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
  "Content-Type" -> "multipart/form-data; boundary=---------------------------198111773715345659193638287702",
  "Origin" -> baseURL,
  "TE" -> "Trailers",
  "Upgrade-Insecure-Requests" -> "1")

 val headers_fire3MB = Map(
  "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
  "Content-Type" -> "multipart/form-data; boundary=---------------------------1748031016249108562799986968",
  "Origin" -> baseURL,
  "TE" -> "Trailers",
  "Upgrade-Insecure-Requests" -> "1")

 val headers_submituploadfire = Map(
  "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
  "Content-Type" -> "multipart/form-data; boundary=---------------------------24522327642503733002897372352",
  "Origin" -> baseURL,
  "TE" -> "Trailers",
  "sec-fetch-dest" -> "document",
  "Upgrade-Insecure-Requests" -> "1",
  "user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36"
 )

 val headers_uploadfile = Map(
  "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
  "Content-Type" -> "multipart/form-data; boundary=---------------------------18500060034595679201711657330",
  "Origin" -> "https://sscs-cor.perftest.platform.hmcts.net",
  "TE" -> "Trailers",
  "Upgrade-Insecure-Requests" -> "1")

 val headers_upload2MBcomplete = Map(
  "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
  "Content-Type" -> "multipart/form-data; boundary=---------------------------3514927590233634601899921967",
  "Origin" -> "https://sscs-cor.perftest.platform.hmcts.net",
  "TE" -> "Trailers",
  "Upgrade-Insecure-Requests" -> "1")

 val headers_statement = Map(
  "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
  "Accept-Encoding" -> "gzip, deflate, br",
  "Accept-Language" -> "en-GB,en-US;q=0.9,en;q=0.8",
  "Origin" -> "https://sscs-cor.perftest.platform.hmcts.net",
  "Sec-Fetch-Dest" -> "document",
  "Sec-Fetch-Mode" -> "navigate",
  "Sec-Fetch-Site" -> "same-origin",
  "Sec-Fetch-User" -> "?1",
  "Upgrade-Insecure-Requests" -> "1",
  "User-Agent" -> "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.80 Safari/537.36",
  "sec-ch-ua" -> """Chromium";v="86", "\"Not\\A;Brand";v="99", "Google Chrome";v="86""",
  "sec-ch-ua-mobile" -> "?0")


}
