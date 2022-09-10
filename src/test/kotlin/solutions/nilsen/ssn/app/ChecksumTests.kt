package solutions.nilsen.ssn.app

import java.lang.IllegalArgumentException
import kotlin.test.*

class ChecksumTests {

    @Test
    fun testCalculatesChecksums() {
        assertEquals("54", Checksum.calculate("030902778"))
        assertEquals("05", Checksum.calculate("030902804"))
        assertEquals("03", Checksum.calculate("250906809"))
        assertEquals("50", Checksum.calculate("250906785"))
        assertEquals("47", Checksum.calculate("250392914"))
    }

    @Test
    fun testYieldsNullIfNoValidChecksumExists() {
        assertEquals(null, Checksum.calculate("030922004"))
    }

    @Test
    fun testCalculateHandlesInvalidInputs() {
        assertFailsWith<IllegalArgumentException> { Checksum.calculate("") }
        assertFailsWith<IllegalArgumentException> { Checksum.calculate("1") }
        assertFailsWith<IllegalArgumentException> { Checksum.calculate("25039291a") }
        assertFailsWith<IllegalArgumentException> { Checksum.calculate("2509068092") }
        assertFailsWith<IllegalArgumentException> { Checksum.calculate("2503929125039291") }
    }

    @Test
    fun testValidatesChecksum() {
        assertTrue { Checksum.validate("06037590647") }
        assertTrue { Checksum.validate("06037590132") }
        assertTrue { Checksum.validate("13117591039") }
        assertTrue { Checksum.validate("24117595575") }

        assertFalse { Checksum.validate("06037459064") }
        assertFalse { Checksum.validate("06037590232") }
        assertFalse { Checksum.validate("13127591039") }
        assertFalse { Checksum.validate("24117544575") }
    }

    @Test
    fun testValidateHandlesInvalidInputs() {
        assertFailsWith<IllegalArgumentException> { Checksum.validate("") }
        assertFailsWith<IllegalArgumentException> { Checksum.validate("1") }
        assertFailsWith<IllegalArgumentException> { Checksum.validate("25039291a") }
        assertFailsWith<IllegalArgumentException> { Checksum.validate("2509068092") }
        assertFailsWith<IllegalArgumentException> { Checksum.validate("2503929125039291") }
    }
}