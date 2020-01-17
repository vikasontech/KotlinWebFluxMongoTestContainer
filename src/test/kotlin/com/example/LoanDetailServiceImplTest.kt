package com.example

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import reactor.test.StepVerifier
import java.math.BigDecimal
import java.time.LocalDate


@RunWith(SpringRunner::class)
@SpringBootTest
class LoanDetailServiceImplTest {

    @Autowired
    private lateinit var loanDetailService: LoanDetailService

    @Test
    fun `save load detail service`() {
        StepVerifier.create(loanDetailService.save(LoanDetail(_id = "1", loanAmount = BigDecimal.valueOf(100000L),
            emiAmount = BigDecimal.valueOf(1000L),
            interestRate = 10F,
            loanCommencedDate = LocalDate.now(),
            numberOfEmi = 120)))
            .expectSubscription()
            .expectNextCount(1)
            .verifyComplete()
    }

}