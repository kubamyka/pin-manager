package com.kmcoding.pinmanager.data.source

import com.kmcoding.pinmanager.domain.model.Pin

object FakeDataSource {
    val fakePins =
        listOf(
            Pin(id = 1, name = "Jacob", code = "123456"),
            Pin(id = 2, name = "PIN code for testing purpose", code = "654321"),
            Pin(id = 3, name = "Third PIN code", code = "333333"),
        )
}
