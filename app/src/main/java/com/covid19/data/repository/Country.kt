package com.covid19.data.repository

data class Country(
    val Country: String,
    val CountryCode: String,
    val Date: String,
    val NewConfirmed: Int,
    val NewDeaths: Int,
    val NewRecovered: Int,
    val Premium: Premium,
    val Slug: String,
    val TotalConfirmed: Int,
    val TotalDeaths: Int,
    val TotalRecovered: Int
)