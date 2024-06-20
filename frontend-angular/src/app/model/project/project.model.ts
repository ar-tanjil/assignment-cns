import { User } from "../authentication/auth.model";

export class ProjectTable{
    constructor(
        public id?: number,
        public name?: string,
        public status?: string,
        public startDate?: Date,
        public endDate?: Date,
        public owner?: User,
        public members?: User[]
    ){}
}

export class ProjectPost{
    constructor(
        public id?: number,
        public name?: string,
        public status?: number,
        public startDate?: Date,
        public endDate?: Date,
        public memberId?: number[]
    ){}
}