import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Person } from '../models/person';

@Injectable({
  providedIn: 'root'
})
export class PersonService {
  private readonly baseUrl = 'http://localhost:8080/api/people';

  constructor(private readonly http: HttpClient) {}

  getAll(): Observable<Person[]> {
    return this.http.get<Person[]>(this.baseUrl);
  }

  getById(id: number): Observable<Person> {
    return this.http.get<Person>(`${this.baseUrl}/${id}`);
  }

  add(person: Person): Observable<Person> {
    return this.http.post<Person>(this.baseUrl, person);
  }

  update(id: number, person: Person): Observable<Person> {
    return this.http.put<Person>(`${this.baseUrl}/${id}`, person);
  }

  remove(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
