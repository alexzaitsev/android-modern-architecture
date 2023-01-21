package com.alexzaitsev.modern.domain

import com.alexzaitsev.modern.data.DataModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.ksp.generated.*

@Module
@ComponentScan
class DomainModule

val domainModules = listOf(DomainModule().module, DataModule().module)
