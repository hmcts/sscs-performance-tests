package uk.gov.hmcts.reform.sscs.performance.utils

object Feeders {




  val DataFeederForUpload = Iterator.continually(Map("service" -> ({
    "Upload"
  }),
    "SignoutNumber" -> ({
      "110"
    })

  ));

  val DataFeederForNoUpload = Iterator.continually(Map("service" -> ({
    "NoUpload"
  }),
    "SignoutNumber" -> ({
      "50"
    })
  ));

  val IACViewDataFeeder = Iterator.continually(Map("service" -> ({
    "IACV"
  }),
    "SignoutNumber" -> ({
      "070"
    })
  ));

  val FPLViewDataFeeder = Iterator.continually(Map("service" -> ({
    "FPLV"
  }),
    "SignoutNumber" -> ({
      "070"
    })
  ));

  val ProDataFeeder = Iterator.continually(Map("service" -> ({
    "PROB"
  }),
    "SignoutNumber" -> ({
      "120"
    })
  ));





}



