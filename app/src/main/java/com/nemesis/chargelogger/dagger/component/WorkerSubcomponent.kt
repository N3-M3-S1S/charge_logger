package com.nemesis.chargelogger.dagger.component

import com.nemesis.chargelogger.dagger.scope.WorkerScope
import com.nemesis.chargelogger.worker.ChargerLoggingWorker
import dagger.Subcomponent

@Subcomponent
@WorkerScope
interface WorkerSubcomponent {
    fun inject(chargerLoggingWorker: ChargerLoggingWorker)

    @Subcomponent.Builder
    interface Builder {
        fun build(): WorkerSubcomponent
    }
}