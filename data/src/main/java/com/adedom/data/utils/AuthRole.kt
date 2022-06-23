package com.adedom.data.utils

sealed class AuthRole(val value: Int) {
    object Auth : AuthRole(1)
    object UnAuth : AuthRole(2)
    object Guest : AuthRole(3)
    object Unknown : AuthRole(99)
}