import json
import pandas as pd

gender_dict = {
    'male':0,
    'female':1
}

gender_dict_shortcut = {
    'male':'m',
    'female':'f'
}
def json_data_to_list( data_path ):
    with open( data_path ) as f:
        data_dict = json.load( f )
    return data_dict

def json_list_to_sublists( json_list ):
    posts = []
    ages = []
    genders = []
    for entry in json_list:
        posts.append( entry['post'])
        ages.append( entry['age'])
        genders.append( entry['gender'])
    return posts, ages,genders

def json_list_to_csv( json_list,csv_output_name, number_data_points=None, short_label_form=False ):
    #csv_output_name = ''
    if number_data_points:
        posts, ages,genders = json_list_to_sublists( json_list[:number_data_points] )
    else:
        posts, ages,genders = json_list_to_sublists( json_list )

    df = pd.DataFrame( posts, columns=["post"] )

    if short_label_form:
        df['gender'] = genders
        df['gender'] = df['gender'].map( gender_dict_shortcut )
    else:
        df['age'] = ages
        df['gender'] = genders
        df['gender_numeric'] = df['gender'].map( gender_dict_shortcut )
    print(df.head())
    print( df.shape )
    df.to_csv( csv_output_name, index=None, sep='\t' )
    #   print(df['gender'].unique())


if __name__ == "__main__":
    train_data_path = 'train.json'
    test_data_path = 'test.json'
    print()

    train_data_list = json_data_to_list( train_data_path )
    test_data_list = json_data_to_list( test_data_path )
    #json_list_to_csv( test_data_list )
    json_list_to_csv( train_data_list,'train_data_1000.csv', number_data_points=1000, short_label_form=True )
    json_list_to_csv( test_data_list,'test_data_1000.csv', number_data_points=1000, short_label_form=True )
    #print(len(test_data_list ))
