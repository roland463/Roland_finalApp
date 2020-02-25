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
public final class QuestionChoicesDao_Impl implements QuestionChoicesDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfQuestionWithChoicesEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateQuestionWithChoice;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllChoicesOfQuestion;

  public QuestionChoicesDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfQuestionWithChoicesEntity = new EntityInsertionAdapter<QuestionWithChoicesEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `answer_choices`(`id`,`question_id`,`ans_choice`,`ans_choice_pos`,`ans_choice_id`,`ans_choice_state`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, QuestionWithChoicesEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getQuestionId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getQuestionId());
        }
        if (value.getAnswerChoice() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAnswerChoice());
        }
        if (value.getAnswerChoicePosition() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getAnswerChoicePosition());
        }
        if (value.getAnswerChoiceId() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getAnswerChoiceId());
        }
        if (value.getAnswerChoiceState() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getAnswerChoiceState());
        }
      }
    };
    this.__preparedStmtOfUpdateQuestionWithChoice = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE answer_choices SET  ans_choice_state = ? WHERE question_id = ? AND ans_choice_pos =?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllChoicesOfQuestion = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM answer_choices";
        return _query;
      }
    };
  }

  @Override
  public void insertAllChoicesOfQuestion(List<QuestionWithChoicesEntity> choices) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfQuestionWithChoicesEntity.insert(choices);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateQuestionWithChoice(String selectState, String questionId, String optionId) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateQuestionWithChoice.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (selectState == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, selectState);
      }
      _argIndex = 2;
      if (questionId == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, questionId);
      }
      _argIndex = 3;
      if (optionId == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, optionId);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateQuestionWithChoice.release(_stmt);
    }
  }

  @Override
  public void deleteAllChoicesOfQuestion() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllChoicesOfQuestion.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllChoicesOfQuestion.release(_stmt);
    }
  }

  @Override
  public String isChecked(String questionId, String optionId) {
    final String _sql = "SELECT ans_choice_state FROM answer_choices WHERE question_id = ? AND ans_choice_pos =?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (questionId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, questionId);
    }
    _argIndex = 2;
    if (optionId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, optionId);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final String _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getString(0);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<QuestionWithChoicesEntity> getAllQuestionsWithChoices(String selected) {
    final String _sql = "SELECT * FROM answer_choices WHERE ans_choice_state =?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (selected == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, selected);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfQuestionId = _cursor.getColumnIndexOrThrow("question_id");
      final int _cursorIndexOfAnswerChoice = _cursor.getColumnIndexOrThrow("ans_choice");
      final int _cursorIndexOfAnswerChoicePosition = _cursor.getColumnIndexOrThrow("ans_choice_pos");
      final int _cursorIndexOfAnswerChoiceId = _cursor.getColumnIndexOrThrow("ans_choice_id");
      final int _cursorIndexOfAnswerChoiceState = _cursor.getColumnIndexOrThrow("ans_choice_state");
      final List<QuestionWithChoicesEntity> _result = new ArrayList<QuestionWithChoicesEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final QuestionWithChoicesEntity _item;
        _item = new QuestionWithChoicesEntity();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpQuestionId;
        _tmpQuestionId = _cursor.getString(_cursorIndexOfQuestionId);
        _item.setQuestionId(_tmpQuestionId);
        final String _tmpAnswerChoice;
        _tmpAnswerChoice = _cursor.getString(_cursorIndexOfAnswerChoice);
        _item.setAnswerChoice(_tmpAnswerChoice);
        final String _tmpAnswerChoicePosition;
        _tmpAnswerChoicePosition = _cursor.getString(_cursorIndexOfAnswerChoicePosition);
        _item.setAnswerChoicePosition(_tmpAnswerChoicePosition);
        final String _tmpAnswerChoiceId;
        _tmpAnswerChoiceId = _cursor.getString(_cursorIndexOfAnswerChoiceId);
        _item.setAnswerChoiceId(_tmpAnswerChoiceId);
        final String _tmpAnswerChoiceState;
        _tmpAnswerChoiceState = _cursor.getString(_cursorIndexOfAnswerChoiceState);
        _item.setAnswerChoiceState(_tmpAnswerChoiceState);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
