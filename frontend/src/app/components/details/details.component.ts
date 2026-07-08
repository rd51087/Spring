import { ChangeDetectionStrategy, Component, DestroyRef, OnInit, inject, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { catchError, map, of, switchMap } from 'rxjs';

import { Person } from '../../models/person';
import { PersonService } from '../../services/person.service';

@Component({
  selector: 'app-details',
  imports: [RouterLink],
  templateUrl: './details.component.html',
  styleUrl: './details.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class DetailsComponent implements OnInit {
  private readonly route = inject(ActivatedRoute);
  private readonly personService = inject(PersonService);
  private readonly destroyRef = inject(DestroyRef);

  readonly person = signal<Person | null>(null);

  ngOnInit(): void {
    this.route.paramMap
      .pipe(
        takeUntilDestroyed(this.destroyRef),
        map((params) => Number(params.get('id'))),
        switchMap((id) => {
          if (Number.isNaN(id)) {
            return of(null);
          }
          return this.personService.getById(id).pipe(catchError(() => of(null)));
        })
      )
      .subscribe((person) => this.person.set(person));
  }
}
