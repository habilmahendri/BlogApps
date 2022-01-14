package com.core.model.repository

import com.core.domain.repository.AppRepository
import com.core.model.data.BlogDataItem
import com.core.model.data.HomeData
import com.core.model.repository.remote.RemoteDataSource
import io.reactivex.Observable

class AppRepositoryImpl constructor(private val remoteDataSource: RemoteDataSource): AppRepository {
    override fun home(): Observable<HomeData> {
        val remoteSource = remoteDataSource.home()
            .flatMap { Observable.just(it.asResult().data) }
            .onErrorResumeNext { t: Throwable -> return@onErrorResumeNext Observable.error(t) }
        return Observable.concatArrayEager(remoteSource)
    }

    override fun detail(id: Int): Observable<BlogDataItem> {
        val remoteSource = remoteDataSource.detail(id)
            .flatMap { Observable.just(it.asResult().data) }
            .onErrorResumeNext { t: Throwable -> return@onErrorResumeNext Observable.error(t) }
        return Observable.concatArrayEager(remoteSource)
    }

    override fun deleteBlog(id: Int): Observable<BlogDataItem> {
        val remoteSource = remoteDataSource.deleteBlog(id)
            .flatMap { Observable.just(it.asResult().data) }
            .onErrorResumeNext { t: Throwable -> return@onErrorResumeNext Observable.error(t) }
        return Observable.concatArrayEager(remoteSource)
    }

    data class Result<out T>(
        val data: T? = null,
        val error: Throwable? = null
    )

    private fun <T> T.asResult(): Result<T> = Result(data = this, error = null)

}