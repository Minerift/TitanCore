package org.vexar.titan.modules.quest.system;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.vexar.titan.modules.quest.QuestEnum;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class QuestData {

    private QuestEnum questEnum;
    private QuestStateEnum questStateEnum;
    private long stateExpireTimestamp;

    // For deserialization
    private QuestData() {}

    public QuestData(QuestEnum questEnum) {
        this.questEnum = questEnum;
        this.questStateEnum = QuestStateEnum.NOT_ACTIVE;
        this.stateExpireTimestamp = -1;
    }

    public QuestEnum getQuestEnum() {
        return questEnum;
    }

    @JsonIgnore
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
