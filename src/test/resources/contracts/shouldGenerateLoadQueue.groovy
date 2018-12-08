import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should generate load via queue"
    label "triggerWork"

    input {
        triggeredBy("triggerWork()")
    }
    outputMessage {
        sentTo "work"
        body("helloworld")
    }
}