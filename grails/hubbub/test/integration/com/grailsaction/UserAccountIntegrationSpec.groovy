package com.grailsaction

import grails.test.spock.IntegrationSpec

class UserAccountIntegrationSpec extends IntegrationSpec {

    def setup() {
    }

    def cleanup() {
    }

    void testFirstSaveEver() {
        def user = new UserAccount(userId: "joe", password: 'secret', homepage: 'http://www.grailsaction.com')
        assertNotNull user.save()
        assertNotNull user.id

        def foundUser = UserAccount.get(user.id)
        assertEquals 'joe', foundUser.userId
    }

    void testSaveAndUpdate() {
        def user = new UserAccount(userId: "joe", password: 'secret', homepage: 'http://www.grailsaction.com')
        assertNotNull user.save()

        def foundUser = UserAccount.get(user.id)
        foundUser.password = 'sesame'
        foundUser.save()

        def editeduser = UserAccount.get(user.id)
        assertEquals 'sesame', editeduser.password
    }

    void testSaveThenDelete() {
        def user = new UserAccount(userId: "joe", password: 'secret', homepage: 'http://www.grailsaction.com')
        assertNotNull user.save()

        def foundUser = UserAccount.get(user.id)
        foundUser.delete()

        assertFalse UserAccount.exists(foundUser.id)
    }

    void testEvilSave() {
        def user = new UserAccount(userId: 'chuck-norris', password: 'tiny', homepage: 'not-a-url')

        assertFalse user.validate()
        assertTrue user.hasErrors()

        def errors = user.errors

        assertEquals "size.toosmall", errors.getFieldError("password").code
        assertEquals "tiny", errors.getFieldError("password").rejectedValue

        assertEquals "url.invalid", errors.getFieldError("homepage").code
        assertEquals "not-a-url", errors.getFieldError("homepage").rejectedValue

        assertNull errors.getFieldError("userId")
    }
}
