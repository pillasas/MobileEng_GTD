<template>
  <ion-item-sliding>
    <ion-item>
      <ion-label>
        <h2>{{task.title}}</h2>
        <h3>{{task.time}}</h3>
        <p>{{task.description}}</p>
      </ion-label>
    </ion-item>
    <ion-item-options side="end">
      <ion-item-option @click="removeTodo(item)">Löschen</ion-item-option>
    </ion-item-options>
  </ion-item-sliding>

    <!-- <ion-item-sliding>
          <ion-item>
            <ion-label>
            <h2>Task 1</h2>
            <h3>Zeitpunkt</h3>
            <p>Beschreibung</p>
          </ion-label>
          </ion-item>
          <ion-item-options side="end">
            <ion-item-option @click="removeTodo(item)">Löschen</ion-item-option>
          </ion-item-options>
        </ion-item-sliding> -->
</template>

<script lang="ts">
import { useTodos } from "@/composables/useTodos";
import { IonItem, IonItemSliding, IonLabel, IonItemOptions } from "@ionic/vue";
import { defineComponent } from "vue";

export default defineComponent({
  name: "TasklistItem",
  components: {
    IonItem,
    IonItemSliding,
    IonLabel,
    IonItemOptions,
  },
  props: ["task"],
  setup() {
    const { removeTodo } = useTodos();
    return { removeTodo };
  },
});
</script>

<style scoped>
</style>