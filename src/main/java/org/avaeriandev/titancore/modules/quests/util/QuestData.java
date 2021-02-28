package org.avaeriandev.titancore.modules.quests.util;

import org.avaeriandev.titancore.modules.quests.QuestEnum;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class QuestData {

    private QuestEnum questEnum;
    private QuestStateEnum questStateEnum;
    private long stateExpireTimestamp;

    public QuestData(QuestEnum questEnum) {
        this.questEnum = questEnum;
        this.questStateEnum = QuestStateEnum.NOT_ACTIVE;
        this.stateExpireTimestamp = -1;
    }

    // Deserialize object from file
    public QuestData(@NotNull Map<String, Object> serialMap) {
        this.questEnum = QuestEnum.valueOf(String.valueOf(serialMap.get("quest")));
        this.questStateEnum = QuestStateEnum.valueOf(String.valueOf(serialMap.get("quest-state")));
        this.stateExpireTimestamp = (long) serialMap.get("state-expire-timestamp");
    }

    public Map<String, Object> serialize() {
        Map<String, Object> serialMap = new HashMap<>();

        serialMap.put("quest", questEnum.name());
        serialMap.put("quest-state", questStateEnum.name());
        serialMap.put("state-expire-timestamp", stateExpireTimestamp);

        return serialMap;
    }

    public QuestEnum getQuestEnum() {
        return questEnum;
    }

    public QuestStateEnum getQuestState() {
        return questStateEnum;
    }

    public long getStateExpireTimestamp() {
        return stateExpireTimestamp;
    }

    public void setQuestStateEnum(QuestStateEnum questStateEnum) {
        this.questStateEnum = questStateEnum;
    }

    public void setStateExpireTimestamp(long stateExpireTimestamp) {
        this.stateExpireTimestamp = stateExpireTimestamp;
    }
}
