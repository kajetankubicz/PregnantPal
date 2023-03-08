package com.example.pregnantpal.model

import java.time.LocalDateTime
import java.util.UUID

data class MaternalData(
    val id: UUID = UUID.randomUUID(),
    val entryDate: LocalDateTime = LocalDateTime.now(),
    val pregnancyTypeChosen: String,
    val fetalCrownRumpLength: String,
    val examinationDate: String,
    val dayOfBirth: String,
    val height: String,
    val weight: String,
    val racialOriginChosen: String,
    val smokeState: Boolean,
    val motherPEState: Boolean,
    val conceptionTypeChosen: String,
    val hypertensionState: Boolean,
    val diabetesFirstState: Boolean,
    val lupusState: Boolean,
    val phospholipidState: Boolean,
    val whenLastPregnancyState: Boolean,
    val meanArterialPressure: String,
    val dateOfBiophysicalMeasurements: String,
    val serumPLGFState: Boolean,
    val serumPAPPAState: Boolean,
)

