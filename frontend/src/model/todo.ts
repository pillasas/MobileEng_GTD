export interface ToDo {
    id?: number;
    projectId?: number;
    categoryId?: number;
    title?: string;
    beschreibung?: string;
    zeitpunkt?: Date;
    done?: boolean;
    //Enum definieren
    priorisierung?: number;
    foto?: Uint16Array;
}