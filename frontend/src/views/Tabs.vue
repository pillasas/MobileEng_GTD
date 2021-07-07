<template>
  <ion-page>
    <ion-tabs>
      <ion-router-outlet></ion-router-outlet>
      <ion-tab-bar slot="bottom">
        <ion-tab-button tab="overview" href="/tabs/overview">
          <ion-icon :icon="flash" />
          <ion-label>Übersicht</ion-label>
        </ion-tab-button>
        
        <ion-tab-button tab="tasks" href="/tabs/tasks">
          <ion-icon :icon="send" />
          <ion-label>Aufgaben</ion-label>
        </ion-tab-button>
                  
        <ion-tab-button tab="projects" href="/tabs/projects">
          <ion-icon :icon="apps" />
          <ion-label>Projekte</ion-label>
        </ion-tab-button>

      </ion-tab-bar>
    </ion-tabs>
  </ion-page>
</template>

<script lang="ts">
import { IonTabBar, IonTabButton, IonTabs, IonLabel, IonIcon, IonPage, IonRouterOutlet } from '@ionic/vue';
import { flash, apps, send } from 'ionicons/icons';

export default {
  name: 'Tabs',
  components: { IonLabel, IonTabs, IonTabBar, IonTabButton, IonIcon, IonPage, IonRouterOutlet },
  setup() {
    return {
      flash, 
      apps, 
      send,
    }
  }
}
</script>