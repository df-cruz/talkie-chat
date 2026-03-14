package com.dfcruz.talkie.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.dfcruz.talkie.data.database.entity.ConversationEntity
import com.dfcruz.talkie.data.database.entity.ConversationMemberEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ConversationDao {

    @Query("SELECT * FROM conversations")
    fun getConversationsFlows(): Flow<List<ConversationEntity>>

    @Query("SELECT * FROM conversations")
    suspend fun getConversations(): List<ConversationEntity>

    @Query("SELECT * FROM conversations WHERE id = :conversationId LIMIT 1")
    suspend fun getConversation(conversationId: String): ConversationEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(conversation: ConversationEntity)

    @Upsert
    suspend fun insert(conversations: List<ConversationEntity>)

    @Update
    suspend fun update(conversation: ConversationEntity)

    @Query("DELETE FROM conversations WHERE id = :conversationId")
    suspend fun delete(conversationId: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMember(member: ConversationMemberEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMembers(members: List<ConversationMemberEntity>)

    @Query("DELETE FROM conversation_members WHERE conversationId = :conversationId AND userId = :userId")
    suspend fun removeMember(conversationId: String, userId: String)

    @Query("UPDATE conversations SET isPinned = :isPinned WHERE id = :conversationId")
    suspend fun pinConversation(conversationId: String, isPinned: Boolean)

    @Query("UPDATE conversations SET isMuted = :isMuted WHERE id = :conversationId")
    suspend fun muteConversation(conversationId: String, isMuted: Boolean)

    @Transaction
    suspend fun upsertConversations(
        conversations: List<ConversationEntity>,
        members: List<ConversationMemberEntity>
    ) {
        insert(conversations)
        addMembers(members)
    }

    @Transaction
    suspend fun createConversationWithMembers(
        conversation: ConversationEntity,
        members: List<ConversationMemberEntity>
    ) {
        insert(conversation)
        addMembers(members)
    }

}