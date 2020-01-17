package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.math.BigDecimal
import java.time.LocalDate

@SpringBootApplication
class KotlinWebFluxMongoTestContainerApplication

fun main(args: Array<String>) {
	runApplication<KotlinWebFluxMongoTestContainerApplication>(*args)
}

@Document
data class LoanDetail(val _id: String,
					  var loanAmount: BigDecimal,
					  var loanCommencedDate: LocalDate,
					  var interestRate: Float,
					  var numberOfEmi: Int,
					  var emiAmount: BigDecimal)


@Repository
interface LoanDetailRepo : ReactiveCrudRepository<LoanDetail, String>


interface LoanDetailService {
	fun save(loanDetail: LoanDetail): Mono<LoanDetail>
}

@Service
class LoanDetailServiceImpl(private val loanDetailRepo:  LoanDetailRepo) : LoanDetailService {

	override fun save(loanDetail: LoanDetail): Mono<LoanDetail> {
		return loanDetailRepo.save(loanDetail)
	}
}
