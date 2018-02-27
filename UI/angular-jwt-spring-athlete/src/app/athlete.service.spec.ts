import { TestBed, inject } from '@angular/core/testing';

import { AthleteService } from './athlete.service';

describe('AthleteService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AthleteService]
    });
  });

  it('should be created', inject([AthleteService], (service: AthleteService) => {
    expect(service).toBeTruthy();
  }));
});
