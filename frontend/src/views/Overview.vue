<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-title>Anstehende Aufgaben</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content>
      <ion-header collapse="condense">
      </ion-header>
      <tasklist></tasklist>
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import { IonPage, IonHeader, IonToolbar, IonTitle, IonContent } from '@ionic/vue';
import App from '@/App.vue';
import Tasklist from '@/components/Tasklist.vue';

export default  {
  name: 'Overview',
  components: {IonPage, IonHeader, IonToolbar, IonTitle, IonContent, Tasklist }
}
</script>