package eu.petrfaruzel.nba.shared

import com.google.gson.Gson
import com.google.gson.JsonParseException
import timber.log.Timber

inline fun<reified T> tryParseJson(json: String?): T? {
    if(json == null) return null
    return try {
        Gson().fromJson(json, T::class.java)
    } catch (e: JsonParseException){
        Timber.w("Parsing exception for class ${T::class.java.simpleName} with result $e")
        null
    }
}

fun<T> T.toJson() : String {
    return Gson().toJson(this)
}