package com.github.premnirmal.ticker.components

import android.content.Context
import com.github.premnirmal.tickerwidget.BuildConfig
import com.github.premnirmal.tickerwidget.R
import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.gms.analytics.HitBuilders
import com.google.android.gms.analytics.Logger
import com.google.android.gms.analytics.Tracker

/**
 * Created by premnirmal on 2/26/16.
 */
internal class AnalyticsImpl(context: Context) : Analytics {

  private val tracker: Tracker

  init {
    val googleAnalytics = GoogleAnalytics.getInstance(context)
    if (BuildConfig.DEBUG) {
      googleAnalytics.logger.logLevel = Logger.LogLevel.VERBOSE
    }
    tracker = googleAnalytics.newTracker(R.xml.event_tracker)
  }

  override fun trackUpdate(action: String, label: String) {
    tracker.send(
        HitBuilders.EventBuilder().setCategory("WidgetStatus")
            .setAction(action).setLabel(label).build())
  }

  override fun trackWidgetUpdate(action: String) {
    tracker.send(
        HitBuilders.EventBuilder().setCategory("WidgetStatus")
            .setAction(action).build())
  }

  override fun trackWidgetSizeUpdate(value: String) {
    tracker.send(HitBuilders.EventBuilder().setCategory("WidgetStatus")
        .setAction("onOptionsChanged").setLabel(value).build())
  }

  override fun trackSettingsChange(action: String, label: String) {
    tracker.send(
        HitBuilders.EventBuilder().setCategory("AppSettingsChange").setAction(action)
            .setLabel(label).build())
  }

  override fun trackRateYes() {

  }

  override fun trackRateNo() {

  }
}
