package me.meowcher.winnerpov.events

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import me.bush.eventbuskotlin.Config
import me.bush.eventbuskotlin.EventBus
import org.apache.logging.log4j.LogManager

object Event
{
    val bushBus : EventBus = EventBus(Config(
            logger = LogManager.getLogger("Bush Bus Kot"),
            parallelScope = CoroutineScope(Dispatchers.Default),
            thirdPartyCompatibility = true,
            annotationRequired = false
    ))
}