package com.credit.feature.home

import androidx.lifecycle.ViewModel
import com.credit.domain.repository.HomeRepository
import com.credit.domain.session.SessionManager
import com.credit.domain.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    private val sessionManager: SessionManager
) : ViewModel(), ContainerHost<HomeState, HomeSideEffect> {

    override val container = container<HomeState, HomeSideEffect>(HomeState()) {
        loadPosts()
    }

    fun loadPosts() = intent {
        reduce { state.copy(isLoading = true, error = null) }
        when (val result = repository.getPosts()) {
            is ApiResult.Success -> reduce {
                state.copy(isLoading = false, posts = result.data, error = null)
            }
            is ApiResult.Error -> {
                reduce { state.copy(isLoading = false, error = result.message) }
                postSideEffect(HomeSideEffect.ShowError(result.message))
            }
        }
    }

    fun logout() = intent {
        sessionManager.logout()
        postSideEffect(HomeSideEffect.NavigateToOnboarding)
    }
}
