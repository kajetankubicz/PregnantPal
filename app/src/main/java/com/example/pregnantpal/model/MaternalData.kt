package com.example.pregnantpal.model

import java.time.LocalDateTime
import java.util.UUID

//Class with data that will be used in classification network

data class MaternalData(
    val id: UUID = UUID.randomUUID(),
    val entryDate: LocalDateTime = LocalDateTime.now(),
    val singleton_or_twins: Int, //1 - singleton ,2 - twins
    val fetus_1: Int,
    val fetus_2: Int,
    val examinationDate: Int, //dd-mm-yyyy
    val date_of_birth: Int, //dd-mm-yyyy
    val height: Int, //Int
    val weight: Int, //Int
    val racial_origin: Int, // 1 - White, 2 - Black, 3 - South Asian, 4 - East Asian, 5 - Mixed
    val smoking: Int, // 1 - Yes, 2 - No
    val previous_preeclampsia: Int, // 1 - Yes, 2 - No
    val conception_method: Int, // 1 - Spontenous, 2 - Ovulatio drugs, 3 - In vitro fertilization
    val ch_hipertension: Int, // 1 - Yes, 2 - No
    val diabetes_type_1: Int, // 1 - Yes, 2 - No
    val diabetes_type_2: Int, // 1 - Yes, 2 - No
    val sle: Int,  // 1 - Yes, 2 - No
    val aps: Int, // 1 - Yes, 2 - No
    val nulliparous: Int,
    val last_pregnancy_pe: Int,
    val last_pregnancy_delivery_weeks: Int,
    val last_pregnancy_delivery_days: Int,
    val map: Float,
    val dateOfBiophysicalMeasurements: Int,
    val plgf: Int,
    val pappa: Int,
    val ga_age: Float,
    val inter_pregancy_interval: Int,
    val utapi: Float,
)

