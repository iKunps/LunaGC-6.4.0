package emu.grasscutter.game.home;

import dev.morphia.annotations.*;
import emu.grasscutter.data.binout.HomeworldDefaultSaveData;
import emu.grasscutter.game.home.suite.HomeSuiteItem;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.net.proto.HomeBlockArrangementInfoOuterClass.HomeBlockArrangementInfo;
import java.util.*;
import java.util.stream.Stream;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder(builderMethodName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HomeBlockItem {
    @Id int blockId;
    boolean unlocked;
    List<HomeFurnitureItem> deployFurnitureList;
    List<HomeFurnitureItem> persistentFurnitureList;
    List<HomeAnimalItem> deployAnimalList;
    List<HomeNPCItem> deployNPCList;
    List<HomeSuiteItem> suiteList;

    public static HomeBlockItem parseFrom(HomeworldDefaultSaveData.HomeBlock homeBlock) {
        // create from default setting
        return HomeBlockItem.of()
                .blockId(homeBlock.getBlockId())
                .unlocked(homeBlock.getFurnitures() != null)
                .deployFurnitureList(
                        homeBlock.getFurnitures() == null
                                ? List.of()
                                : homeBlock.getFurnitures().stream().map(HomeFurnitureItem::parseFrom).toList())
                .persistentFurnitureList(
                        homeBlock.getPersistentFurnitures() == null
                                ? List.of()
                                : homeBlock.getPersistentFurnitures().stream()
                                        .map(HomeFurnitureItem::parseFrom)
                                        .toList())
                .deployAnimalList(List.of())
                .deployNPCList(List.of())
                .suiteList(List.of())
                .build();
    }

    public void update(HomeBlockArrangementInfo homeBlockArrangementInfo, Player owner) {
        this.blockId = homeBlockArrangementInfo.getBlockId();

        this.deployFurnitureList =
                // homeBlockArrangementInfo.getDeployFurniureListList().stream() // field not in current proto
                //         .map(HomeFurnitureItem::parseFrom)
                //         .toList();
                List.of();

        this.persistentFurnitureList =
                // homeBlockArrangementInfo.getPersistentFurnitureListList().stream() // field not in current proto
                //         .map(HomeFurnitureItem::parseFrom)
                //         .toList();
                List.of();

        this.deployAnimalList =
                homeBlockArrangementInfo.getDeployAnimalListList().stream()
                        .map(HomeAnimalItem::parseFrom)
                        .toList();

        this.deployNPCList =
                // homeBlockArrangementInfo.getDeployNpcListList().stream() // field not in current proto
                //         .map(homeNpcData -> HomeNPCItem.parseFrom(homeNpcData, owner))
                //         .toList();
                List.of();

        this.suiteList =
                homeBlockArrangementInfo.getFurnitureSuiteListList().stream()
                        .map(HomeSuiteItem::parseFrom)
                        .toList();
    }

    public int calComfort() {
        return this.deployFurnitureList.stream().mapToInt(HomeFurnitureItem::getComfort).sum();
    }

    public HomeBlockArrangementInfo toProto() {
        var proto =
                HomeBlockArrangementInfo.newBuilder()
                        .setBlockId(blockId)
                        .setIsUnlocked(unlocked)
                        .setComfortValue(calComfort());

        this.reassignIfNull();

        // this.deployFurnitureList.forEach(f -> proto.addDeployFurniureList(f.toProto())); // field not in current proto
        // this.persistentFurnitureList.forEach(f -> proto.addPersistentFurnitureList(f.toProto())); // field not in current proto
        this.deployAnimalList.forEach(f -> proto.addDeployAnimalList(f.toProto()));
        // this.deployNPCList.forEach(f -> proto.addDeployNpcList(f.toProto())); // field not in current proto
        this.suiteList.forEach(f -> proto.addFurnitureSuiteList(f.toProto()));

        return proto.build();
    }

    // TODO implement farm field.
    public List<? extends HomeMarkPointProtoFactory> getMarkPointProtoFactories() {
        this.reassignIfNull();

        return Stream.of(
                        this.deployFurnitureList,
                        this.persistentFurnitureList,
                        this.deployNPCList,
                        this.suiteList)
                .flatMap(Collection::stream)
                .toList();
    }

    public void reassignIfNull() {
        if (this.deployFurnitureList == null) {
            this.deployFurnitureList = List.of();
        }
        if (this.persistentFurnitureList == null) {
            this.persistentFurnitureList = List.of();
        }
        if (this.deployAnimalList == null) {
            this.deployAnimalList = List.of();
        }
        if (this.deployNPCList == null) {
            this.deployNPCList = List.of();
        }
        if (this.suiteList == null) {
            this.suiteList = List.of();
        }
    }
}
