package kr.cafemoca.achivements;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.*;

public class Listener implements org.bukkit.event.Listener {
    private boolean isGameStopped = false;

    @EventHandler
    public void onAdvancementDone(PlayerAdvancementDoneEvent e) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            for (String c : p.getAdvancementProgress(e.getAdvancement()).getRemainingCriteria())
                p.getAdvancementProgress(e.getAdvancement()).awardCriteria(c);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        checkPlayersAndStopGame();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        checkPlayersAndStopGame();
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (isGameStopped) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if (isGameStopped) {
            e.setCancelled(true);
        }
    }

    public void checkPlayersAndStopGame() { // 작명센스 존나딸리네;
        if (Bukkit.getWhitelistedPlayers().size() != Bukkit.getOnlinePlayers().size()) {
            Bukkit.broadcastMessage("[Server] 인원이 모자람을 감지했습니다. 게임을 멈춥니다.");
            isGameStopped = true;
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tick freeze");
            return;
        } else {
            Bukkit.broadcastMessage("[Server] 게임을 재개합니다.");
            isGameStopped = false;
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tick unfreeze");
        }
    }
}
