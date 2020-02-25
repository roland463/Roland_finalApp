package com.spk.questionnaire.questions.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.spk.questionnaire.questions.qdb.QuestionChoicesDao;
import com.spk.questionnaire.questions.qdb.QuestionChoicesDao_Impl;
import com.spk.questionnaire.questions.qdb.QuestionDao;
import com.spk.questionnaire.questions.qdb.QuestionDao_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class AppDatabase_Impl extends AppDatabase {
  private volatile QuestionChoicesDao _questionChoicesDao;

  private volatile QuestionDao _questionDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `answer_choices` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `question_id` TEXT, `ans_choice` TEXT, `ans_choice_pos` TEXT, `ans_choice_id` TEXT, `ans_choice_state` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `questions` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `question_id` INTEGER NOT NULL, `question` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"8e4aa8e37b13fecf5857071c6006a847\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `answer_choices`");
        _db.execSQL("DROP TABLE IF EXISTS `questions`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsAnswerChoices = new HashMap<String, TableInfo.Column>(6);
        _columnsAnswerChoices.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsAnswerChoices.put("question_id", new TableInfo.Column("question_id", "TEXT", false, 0));
        _columnsAnswerChoices.put("ans_choice", new TableInfo.Column("ans_choice", "TEXT", false, 0));
        _columnsAnswerChoices.put("ans_choice_pos", new TableInfo.Column("ans_choice_pos", "TEXT", false, 0));
        _columnsAnswerChoices.put("ans_choice_id", new TableInfo.Column("ans_choice_id", "TEXT", false, 0));
        _columnsAnswerChoices.put("ans_choice_state", new TableInfo.Column("ans_choice_state", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAnswerChoices = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAnswerChoices = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAnswerChoices = new TableInfo("answer_choices", _columnsAnswerChoices, _foreignKeysAnswerChoices, _indicesAnswerChoices);
        final TableInfo _existingAnswerChoices = TableInfo.read(_db, "answer_choices");
        if (! _infoAnswerChoices.equals(_existingAnswerChoices)) {
          throw new IllegalStateException("Migration didn't properly handle answer_choices(com.spk.questionnaire.questions.qdb.QuestionWithChoicesEntity).\n"
                  + " Expected:\n" + _infoAnswerChoices + "\n"
                  + " Found:\n" + _existingAnswerChoices);
        }
        final HashMap<String, TableInfo.Column> _columnsQuestions = new HashMap<String, TableInfo.Column>(3);
        _columnsQuestions.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsQuestions.put("question_id", new TableInfo.Column("question_id", "INTEGER", true, 0));
        _columnsQuestions.put("question", new TableInfo.Column("question", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysQuestions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesQuestions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoQuestions = new TableInfo("questions", _columnsQuestions, _foreignKeysQuestions, _indicesQuestions);
        final TableInfo _existingQuestions = TableInfo.read(_db, "questions");
        if (! _infoQuestions.equals(_existingQuestions)) {
          throw new IllegalStateException("Migration didn't properly handle questions(com.spk.questionnaire.questions.qdb.QuestionEntity).\n"
                  + " Expected:\n" + _infoQuestions + "\n"
                  + " Found:\n" + _existingQuestions);
        }
      }
    }, "8e4aa8e37b13fecf5857071c6006a847", "c2829748d8646cdae5041f33328e2452");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "answer_choices","questions");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `answer_choices`");
      _db.execSQL("DELETE FROM `questions`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public QuestionChoicesDao getQuestionChoicesDao() {
    if (_questionChoicesDao != null) {
      return _questionChoicesDao;
    } else {
      synchronized(this) {
        if(_questionChoicesDao == null) {
          _questionChoicesDao = new QuestionChoicesDao_Impl(this);
        }
        return _questionChoicesDao;
      }
    }
  }

  @Override
  public QuestionDao getQuestionDao() {
    if (_questionDao != null) {
      return _questionDao;
    } else {
      synchronized(this) {
        if(_questionDao == null) {
          _questionDao = new QuestionDao_Impl(this);
        }
        return _questionDao;
      }
    }
  }
}
