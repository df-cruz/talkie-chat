package com.dfcruz.talkie.system

import android.content.Context
import android.provider.ContactsContract
import android.util.Log

class ContactsProvider(
    private val context: Context
) {

    fun getContacts(): List<SystemContact> {
        Log.i("ContactsProvider", "getContacts")

        val contacts = mutableListOf<SystemContact>()

        val projection = arrayOf(
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts.HAS_PHONE_NUMBER
        )

        val cursor = context.contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            projection,
            null,
            null,
            ContactsContract.Contacts.DISPLAY_NAME + " ASC"
        )

        cursor?.use {
            val idIndex = it.getColumnIndexOrThrow(ContactsContract.Contacts._ID)
            val nameIndex = it.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME)
            val hasPhoneIndex = it.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER)

            while (it.moveToNext()) {
                val id = it.getString(idIndex)
                val name = it.getString(nameIndex)
                val hasPhone = it.getInt(hasPhoneIndex)

                var phone: String? = null

                if (hasPhone > 0) {
                    phone = getPhoneNumber(context, id)
                }

                Log.d("ContactsProvider", "getContacts - name=$name")

                contacts.add(SystemContact(id = id, name = name, contact = phone))
            }
        }

        cursor?.close()

        return contacts
    }

    private fun getPhoneNumber(context: Context, contactId: String): String? {
        val cursor = context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER),
            "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} = ?",
            arrayOf(contactId),
            null
        )

        cursor?.use {
            if (it.moveToFirst()) {
                val numberIndex = it.getColumnIndexOrThrow(
                    ContactsContract.CommonDataKinds.Phone.NUMBER
                )
                return it.getString(numberIndex)
            }
        }

        return null
    }
}