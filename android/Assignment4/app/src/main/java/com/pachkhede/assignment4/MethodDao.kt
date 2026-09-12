package com.pachkhede.assignment4

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MethodDao {

    @Insert
    suspend fun insertMethod(method: StringMethod)

    @Insert
    suspend fun insertAll(methods : List<StringMethod>)

    @Query("SELECT * FROM string_methods")
    suspend fun getAllMethods() : List<StringMethod>

    @Query("DELETE FROM string_methods WHERE id = :id")
    suspend fun deleteMethod(id: Int)

    @Query("""
        SELECT * FROM string_methods
        WHERE
        name LIKE '%' || :query || '%'
        OR
        description LIKE '%' || :query || '%'
    """)
    suspend fun searchMethod(query: String) : List<StringMethod>

    @Query("SELECT COUNT(*) FROM string_methods")
    suspend fun getCount(): Int



}