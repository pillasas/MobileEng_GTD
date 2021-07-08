<template>
  <ion-item-sliding>
    <ion-item>
      <ion-label>
        <h2>{{todoItem.title}}</h2>
        <h3>{{todoItem.time}}</h3>
        <p>{{todoItem.description}}</p>
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
import { IonItem, IonItemSliding, IonLabel, IonItemOptions, IonItemOption } from "@ionic/vue";
import { defineComponent } from "vue";

export default defineComponent({
  name: "ToDoListItem",
  components: {
    IonItem,
    IonItemSliding,
    IonLabel,
    IonItemOptions,
    IonItemOption
  },
  props: ["todoItem"],
  setup() {
    const { removeTodo } = useTodos();
    return { removeTodo };
  },
});
</script>

<style scoped>
</style>