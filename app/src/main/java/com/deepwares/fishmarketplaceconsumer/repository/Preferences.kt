package com.deepwares.fishmarketplaceconsumer.repository

import android.content.Context
import android.content.SharedPreferences
import android.preference.Preference
import com.deepwares.fishmarketplaceconsumer.R

class Preferences {

    companion object {

        @JvmStatic
        val SHOULD_SHOW_CREATE_TOOLTIP = "should_show_create_tooltip"

        @JvmStatic
        val SHOULD_SHOW_SUBMIT_TOOLTIP = "should_show_submit_tooltip"

        @JvmStatic
        fun getUserId(context: Context): String? {
            val preferences = context.getSharedPreferences(
                context.getString(R.string.app_name),
                Context.MODE_PRIVATE
            )
            return preferences.getString("userId", null)
        }

        @JvmStatic
        fun getName(context: Context): String? {
            val preferences = context.getSharedPreferences(
                context.getString(R.string.app_name),
                Context.MODE_PRIVATE
            )
            return preferences.getString("name", null)
        }

        @JvmStatic
        fun setUserId(context: Context, userId: String?) {
            val preferences = context.getSharedPreferences(
                context.getString(R.string.app_name),
                Context.MODE_PRIVATE
            )
            preferences.edit().putString("userId", userId).apply()
        }

        @JvmStatic
        fun setName(context: Context, name: String?) {
            val preferences = context.getSharedPreferences(
                context.getString(R.string.app_name),
                Context.MODE_PRIVATE
            )
            preferences.edit().putString("name", name).apply()
        }

        @JvmStatic
        fun shouldShowCreateTooltip(context: Context): Boolean {
            return getCreateTooltipCount(context) > 0
        }

        @JvmStatic
        fun createTooltipShown(context: Context) {
            val preferences = context.getSharedPreferences(
                context.getString(R.string.app_name),
                Context.MODE_PRIVATE
            )
            var tooltipCount = getCreateTooltipCount(context)
            if (tooltipCount > 0) {
                tooltipCount--
            }
            preferences.edit().putInt(SHOULD_SHOW_CREATE_TOOLTIP, tooltipCount).apply()
        }


        @JvmStatic
        fun getCreateTooltipCount(context: Context): Int {
            val preferences = context.getSharedPreferences(
                context.getString(R.string.app_name),
                Context.MODE_PRIVATE
            )
            return preferences.getInt(SHOULD_SHOW_CREATE_TOOLTIP, 3)
        }


        @JvmStatic
        fun shouldShowSubmitTooltip(context: Context): Boolean {
            return getSubmitTooltipCount(context) > 0
        }

        @JvmStatic
        fun submitTooltipShown(context: Context) {
            val preferences = context.getSharedPreferences(
                context.getString(R.string.app_name),
                Context.MODE_PRIVATE
            )
            var tooltipCount = getSubmitTooltipCount(context)
            if (tooltipCount > 0) {
                tooltipCount--
            }
            preferences.edit().putInt(SHOULD_SHOW_SUBMIT_TOOLTIP, tooltipCount).apply()
        }


        @JvmStatic
        fun getSubmitTooltipCount(context: Context): Int {
            val preferences = context.getSharedPreferences(
                context.getString(R.string.app_name),
                Context.MODE_PRIVATE
            )
            return preferences.getInt(SHOULD_SHOW_SUBMIT_TOOLTIP, 3)
        }

        @JvmStatic
        fun logout(context: Context) {
            val preferences = context.getSharedPreferences(
                context.getString(R.string.app_name),
                Context.MODE_PRIVATE
            )
            preferences.edit().clear().apply()
        }
    }
}