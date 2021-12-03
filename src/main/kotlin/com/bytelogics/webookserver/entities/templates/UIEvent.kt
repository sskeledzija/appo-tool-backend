package com.bytelogics.webookserver.entities.templates

import java.time.Instant
import java.time.LocalTime
import java.util.*

/**
 * Events designed by PrimeReact FullCalendar API documentation. More about events for this calendar can be
 * found at: @see https://fullcalendar.io/docs/event-object
 */
class UIEvent(val id: String = UUID.randomUUID().toString(),
              val groupId: String, // userId
              val start: LocalTime,
              val end: LocalTime?,
              val startStr: String?, // representing start date
              val endStr: String?, // representing end date
              val title: String?,   // should contain person names + service
              val url: String?,
              val display: EventDisplay,
              /* The value overriding the eventOverlap setting for this specific event. If false,
              prevents this event from being dragged/resized over other events. Also prevents other
              events from being dragged/resized over this event. Does not accept a function. */
              val overlap: Boolean,
              /* list of constraints for dragging */
              val constraint: List<UIEventPeriod>,
              /* Can be used any of the CSS color formats such #f00, #ff0000, rgb(255,0,0), or red.
              * This option can be overridden on a per-source basis with the backgroundColor Event Source Object option
              * or on a per-event basis with the backgroundColor Event Object option.*/
              val backgroundColor: String,
              val borderColor: String, // same as for background color,
              val textColor: String, // same as for background color
              /* A plain object holding miscellaneous other properties specified during parsing. Receives properties in
              the explicitly given extendedProps hash as well as other non-standard properties.*/
              val extendedProps: String,
              val allDay: Boolean,
) {
    /**
     *  'auto' (default) - When in daygrid, renders the event as a solid rectangle if it is all-day or multi-day. If a timed event, will render it with a dot. When in other views, will render normally.
        'block' - When in daygrid, renders the event as a solid rectangle. When in other views, will render normally.
        'list-item' - When in daygrid, renders the event with a dot. When in other views, will render normally.
        'background' - See the background events article.
        'inverse-background' - Like 'background', but fills the reverse space. See the background events article.
        'none' - Won’t render the event at all.
     */
    enum class EventDisplay(type: String) { AUTO("auto"), BLOCK("block"), LIST_ITEM("list-item"),
        BACKGROUND("background"), INVERSE_BACKGROUND("inverse-background"), NONE("none") }
}