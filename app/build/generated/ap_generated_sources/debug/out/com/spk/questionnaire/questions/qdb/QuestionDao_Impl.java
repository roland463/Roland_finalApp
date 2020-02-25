package com.spk.questionnaire.questions.qdb;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class QuestionDao_Impl implements QuestionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfQuestionEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllQuestions;

  public QuestionDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfQuestionEntity = new EntityInsertionAdapter<QuestionEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `questions`(`id`,`question_id`,`question`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, QuestionEntity value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getQuestionId());
        if (value.getQuestion() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getQuestion());
        }
      }
    };
    this.__preparedStmtOfDeleteAllQuestions = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM questions";
        return _query;
      }
    };
  }

  @Override
  public void insertAllQuestions(List<QuestionEntity> questions) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfQuestionEntity.insert(questions);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllQuestions() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllQuestions.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllQuestions.release(_stmt);
    }
  }

  @Override
  public List<QuestionEntity> getAllQuestions() {
    final String _sql = "SELECT * FROM questions";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfQuestionId = _cursor.getColumnIndexOrThrow("question_id");
      final int _cursorIndexOfQuestion = _cursor.getColumnIndexOrThrow("question");
      final List<QuestionEntity> _result = new ArrayList<QuestionEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final QuestionEntity _item;
        _item = new QuestionEntity();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item.setId(_tmpId);
        final int _tmpQuestionId;
        _tmpQuestionId = _cursor.getInt(_cursorIndexOfQuestionId);
        _item.setQuestionId(_tmpQuestionId);
        final String _tmpQuestion;
        _tmpQuestion = _cursor.getString(_cursorIndexOfQuestion);
        _item.setQuestion(_tmpQuestion);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
