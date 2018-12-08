import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should generate some load"
    request {
        url "/generate"
        method GET()
    }
    response {
        status 200
        body '''expected response" + System.lineSeparator() + 
                "expected response" + System.lineSeparator() +
                "expected response" + System.lineSeparator() +
                "expected response" + System.lineSeparator() +
                "expected response" + System.lineSeparator() +
                "expected response" + System.lineSeparator() +
                "expected response" + System.lineSeparator() +
                "expected response" + System.lineSeparator() +
                "expected response" + System.lineSeparator() +
                "expected response'''
    }
}