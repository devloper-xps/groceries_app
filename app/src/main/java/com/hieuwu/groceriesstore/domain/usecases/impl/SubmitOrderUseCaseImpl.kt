package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.OrderRepository
import com.hieuwu.groceriesstore.di.IoDispatcher
import com.hieuwu.groceriesstore.domain.usecases.SubmitOrderUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SubmitOrderUseCaseImpl @Inject constructor(
    private val orderRepository: OrderRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : SubmitOrderUseCase {
    override suspend fun execute(input: SubmitOrderUseCase.Input): SubmitOrderUseCase.Output {
        return withContext(ioDispatcher) {
            val result = orderRepository.sendOrderToServer(input.order)
            SubmitOrderUseCase.Output(result)
        }
    }
}