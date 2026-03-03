package emu.grasscutter.game.dungeons;

import java.util.List;
import lombok.*;

@Data
@AllArgsConstructor
public class DungeonTrialTeam {
    List<Integer> trialAvatarIds;
    int grantReason;
}
