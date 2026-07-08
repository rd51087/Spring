import { Injectable } from '@angular/core';

import { Person } from '../models/person';

@Injectable({
  providedIn: 'root'
})
export class PersonService {
  private readonly storageKey = 'lab12.people';

  getAll(): Person[] {
    return this.readPeople();
  }

  getByIndex(index: number): Person | null {
    const people = this.readPeople();
    return people[index] ?? null;
  }

  add(person: Person): void {
    const people = this.readPeople();
    people.push(person);
    this.writePeople(people);
  }

  remove(index: number): void {
    const people = this.readPeople();
    if (index < 0 || index >= people.length) {
      return;
    }
    people.splice(index, 1);
    this.writePeople(people);
  }

  private readPeople(): Person[] {
    const raw = localStorage.getItem(this.storageKey);
    if (!raw) {
      return [];
    }
    try {
      const parsed = JSON.parse(raw) as Person[];
      return Array.isArray(parsed) ? parsed : [];
    } catch {
      return [];
    }
  }

  private writePeople(people: Person[]): void {
    localStorage.setItem(this.storageKey, JSON.stringify(people));
  }
}
