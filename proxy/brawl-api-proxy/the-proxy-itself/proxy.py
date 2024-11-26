from flask import Flask, jsonify, request
import requests
from urllib.parse import quote

proxy = Flask(__name__)

# Base URL of the external API
BASE_URL = 'https://api.brawlstars.com/v1'

def forward_request(path):
    # Extract the API key from the request headers
    # api_key = request.headers.get('Authorization')
    
    # if not api_key:
    #    return jsonify({'message': 'Authorization header is missing'}), 400

    # Full URL of the external API endpoint
    external_api_url = f'{BASE_URL}{path}'
    
    # Set up the headers for the request to the external API
    headers = {
            'Authorization': 'Bearer <token>'
    }

    # Make a GET request to the external API
    response = requests.get(external_api_url, headers=headers)
    
    # Return the JSON response from the external API
    if response.status_code == 200:
        return jsonify(response.json())
    else:
        return jsonify({'message': 'Failed to fetch data from external API'}), response.status_code

@proxy.route('/players/<playerTag>/battlelog', methods=['GET'])
def get_player_battlelog(playerTag):
    playerTag = quote(playerTag, safe='')
    return forward_request(f'/players/{playerTag}/battlelog')

@proxy.route('/players/<playerTag>', methods=['GET'])
def get_player_info(playerTag):
    playerTag = quote(playerTag, safe='')
    return forward_request(f'/players/{playerTag}')

@proxy.route('/clubs/<clubTag>/members', methods=['GET'])
def get_club_members(clubTag):
    clubTag = quote(clubTag, safe='')
    return forward_request(f'/clubs/{clubTag}/members')

@proxy.route('/clubs/<clubTag>', methods=['GET'])
def get_club_info(clubTag):
    clubTag = quote(clubTag, safe='')
    return forward_request(f'/clubs/{clubTag}')

@proxy.route('/rankings/<countryCode>/clubs', methods=['GET'])
def get_club_rankings(countryCode):
    return forward_request(f'/rankings/{countryCode}/clubs')

@proxy.route('/rankings/<countryCode>/brawlers/<brawlerId>', methods=['GET'])
def get_brawler_rankings(countryCode, brawlerId):
    return forward_request(f'/rankings/{countryCode}/brawlers/{brawlerId}')

@proxy.route('/rankings/<countryCode>/players', methods=['GET'])
def get_player_rankings(countryCode):
    return forward_request(f'/rankings/{countryCode}/players')

@proxy.route('/brawlers', methods=['GET'])
def get_brawlers():
    return forward_request('/brawlers')

@proxy.route('/brawlers/<brawlerId>', methods=['GET'])
def get_brawler_info(brawlerId):
    return forward_request(f'/brawlers/{brawlerId}')

@proxy.route('/events/rotation', methods=['GET'])
def get_event_rotation():
    return forward_request('/events/rotation')

if __name__ == '__main__':
    proxy.run(debug=True)
