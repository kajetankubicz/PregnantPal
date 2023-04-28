package com.example.pregnantpal.model

import java.time.LocalDateTime
import java.util.UUID

//Class with data that will be used in classification network

data class MaternalData(
    val id: UUID = UUID.randomUUID(),
    val entryDate: LocalDateTime = LocalDateTime.now(),
    val singleton_or_twins: Long, //1 - singleton ,2 - twins
    val fetus_1: Long,
    val fetus_2: Long,
    val examinationDate: Long, //dd-mm-yyyy
    val dayOfBirth: Long, //dd-mm-yyyy
    val height: Long, //Int
    val weight: Long, //Int
    val racial_origin: Long, // 1 - White, 2 - Black, 3 - South Asian, 4 - East Asian, 5 - Mixed
    val smoking: Long, // 1 - Yes, 2 - No
    val previous_preeclampsia: Long, // 1 - Yes, 2 - No
    val conception_method: Long, // 1 - Spontenous, 2 - Ovulatio drugs, 3 - In vitro fertilization
    val ch_hipertension: Long, // 1 - Yes, 2 - No
    val diabetes_type_1: Long, // 1 - Yes, 2 - No
    val diabetes_type_2: Long, // 1 - Yes, 2 - No
    val sle: Long,  // 1 - Yes, 2 - No
    val aps: Long, // 1 - Yes, 2 - No
    val nulliparous: Long,
    val last_pregnancy_pe: Int,
    val last_pregnancy_delivery_weeks: Int,
    val last_pregnancy_delivery_days: Int,
    val map: Float,
    val dateOfBiophysicalMeasurements: Long,
    val plgf: Long,
    val pappa: Long,
    val ga_age: Long,
    val inter_pregancy_interval: Int,
    val utapi: Float
)

