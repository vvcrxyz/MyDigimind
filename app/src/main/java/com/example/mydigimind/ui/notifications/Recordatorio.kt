package com.example.mydigimind.ui.notifications

import java.io.Serializable


data class Recordatorio(var dias: String, var tiempo: String, var nombre: String): Serializable {
}