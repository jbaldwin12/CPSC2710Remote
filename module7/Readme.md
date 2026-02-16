# Nutrition Tracker

A simple JavaFX application for tracking daily meals, calories, and protein intake.

## Inspiration

My wife and I recently decided to start monitoring our nutrition more and have a more
specific workout plan, so I thought this nutrition tracker would be a good place to 
start in helping with that journey.

## Vision

My vision for Nutrition Tracker is for it to evolve to include nutritional goal setting,
as well as workout tracking and planning.

## Basic Functionality

Nutrition Tracker helps you log and monitor daily food intake with these core features:

### Meal Logging
- Add meals with name, date, calories, and protein
- Track optional notes for each meal
- View all logged meals in an organized table

### Daily Summary
- Display of total calories consumed today
- Display of total protein consumed today
- Compare your intake against daily goals (2000 calories, 125g protein)

### Meal Management
- Delete meals if you need to make corrections
- Meals can be organized

## How to Use

### Getting Started
1. Launch the application - the main dashboard will open
2. You'll see your daily summary at the top (starts at 0/2000 cal and 0/125g protein)
3. The meal log table below shows all your logged meals

### Adding a Meal
1. Click the **"Add Meal"** button (or press `Ctrl+M`)
2. A separate window will open with a form
3. Fill in the details:
    - **Date**: Defaults to today (change if logging a past meal)
    - **Meal Name**: What you ate
    - **Calories**: Total calories for the meal
    - **Protein**: Grams of protein
    - **Notes** (optional): Any additional details you want to remember
4. Click **"Save"** to add the meal
5. The meal appears in your table and the summary updates automatically

### Deleting a Meal
1. Click on a meal in the table to select it
2. Click the **"Delete"** button (or press `Ctrl+D`)
3. The meal is removed and your summary updates

### Using Keyboard Shortcuts
- `Ctrl+M` - Add new meal
- `Ctrl+D` - Delete selected meal
- `Ctrl+Q` - Exit application
- `F1` - Show About dialog

### Viewing Your Progress
- The summary cards at the top show your daily totals
- Format: "Current / Goal"

## Functionality Status

### Currently Implemented

- Main Dashboard with daily nutrition summary
- Add meals with date, name, calories, protein, and notes
- Delete meals from the log
- Daily totals calculation
- Meal table displaying all entries
- Date selection for logging past meals
- Menu system with keyboard shortcuts (Ctrl+M, Ctrl+D, Ctrl+Q, F1)
- Custom CSS styling with mainly blue color theme

### Not Yet Implemented

- Data persistence (save meals between sessions)
- Edit existing meal entries
- Custom daily nutrition goals
- Weekly/monthly nutrition view
- Search and filter meals
- Export data to CSV/PDF
- Track additional nutrients
- Workout management systems

## Screenshots

### Main Menu
![Main Menu](screenshots/NutritionTrackerMainMenu.png)

### Add Meal Window
![Add Meal Window](screenshots/AddMealWindow.png)

