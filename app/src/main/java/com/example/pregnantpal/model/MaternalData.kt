package com.example.PregnantPal.model

import java.time.LocalDateTime
import java.util.UUID

//Class with data that will be used in classification network

data class MaternalData(
    val id: UUID = UUID.randomUUID(),
    val entryDate: LocalDateTime = LocalDateTime.now(),
    val singleton_or_twins: String, //1 - singleton ,2 - twins int
    val fetus_1: String,
    val fetus_2: String,
    val examinationDate: String, //dd-mm-yyyy
    val dayOfBirth: String, //dd-mm-yyyy
    val height: String, //Int
    val weight: String, //Int
    val racial_origin: String, // 1 - White, 2 - Black, 3 - South Asian, 4 - East Asian, 5 - Mixed
    val smoking: Boolean, // 1 - Yes, 2 - No
    val previous_preeclampsia: Boolean, // 1 - Yes, 2 - No
    val conception_method: String, // 1 - Spontenous, 2 - Ovulatio drugs, 3 - In vitro fertilization
    val ch_hipertension: Boolean, // 1 - Yes, 2 - No
    val diabetes_type_1: Boolean, // 1 - Yes, 2 - No
    val diabetes_type_2: Boolean, // 1 - Yes, 2 - No
    val SLE: Boolean,  // 1 - Yes, 2 - No
    val APS: Boolean, // 1 - Yes, 2 - No
    val nulliparous: Boolean,
    val last_pregnancy_pe: Int,
    val last_pregnancy_delivery_weeks: Int,
    val last_pregnancy_delivery_days: Int,
    val MAP: String,
    val dateOfBiophysicalMeasurements: String,
    val plgf: Boolean,
    val pappa: Boolean,
    val ga_age: Int,
    val inter_pregancy_interval: Int
)

