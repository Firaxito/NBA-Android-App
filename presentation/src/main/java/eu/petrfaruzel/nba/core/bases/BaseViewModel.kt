package eu.petrfaruzel.nba.core.bases

import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent

/**
 * Primary ViewModel Interface extending ViewModel
 *  also with KoinComponent, which is necessary for DI
 *  via Koin into Composable
 */
abstract class BaseViewModel: ViewModel(), KoinComponent